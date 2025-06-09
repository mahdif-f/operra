// ✅ نسخه ارتقاء یافته DynamicCrudForm با پشتیبانی از دیالوگ lookup و پیجینگ
'use client';

import { useState, useEffect } from 'react';
import { CrudContextDto, FieldDto, FieldType, LookupResultDto } from '@/types/crud';
import { InputText } from 'primereact/inputtext';
import { InputNumber } from 'primereact/inputnumber';
import { Calendar } from 'primereact/calendar';
import { Checkbox } from 'primereact/checkbox';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { fetchLookupOptions } from '@/app/api/metaApi';

interface Props {
    context: CrudContextDto;
    onSubmit: (data: Record<string, any>) => void;
}

export default function DynamicCrudForm({ context, onSubmit }: Props) {
    const [form, setForm] = useState<Record<string, any>>({});
    const [lookupData, setLookupData] = useState<Record<string, LookupResultDto[]>>({});
    const [dialogVisible, setDialogVisible] = useState(false);
    const [activeLookupId, setActiveLookupId] = useState<string | null>(null);
    const [selectedLookupItem, setSelectedLookupItem] = useState<any>(null);

    const handleChange = (name: string, value: any) => {
        setForm(prev => ({ ...prev, [name]: value }));
    };

    const openLookup = async (lookupId: string, fieldName: string) => {
        if (!lookupData[lookupId]) {
            const result = await fetchLookupOptions(lookupId);
            setLookupData(prev => ({ ...prev, [lookupId]: result }));
        }
        setActiveLookupId(fieldName);
        setDialogVisible(true);
    };

    const confirmLookupSelection = () => {
        if (activeLookupId && selectedLookupItem) {
            handleChange(activeLookupId, selectedLookupItem.value);
            setDialogVisible(false);
            setSelectedLookupItem(null);
        }
    };

    const renderField = (field: FieldDto) => {
        const { name, label, type, readonly, placeholder, options, lookupId } = field;
        const lookupOptions = lookupId ? lookupData[lookupId] : undefined;

        switch (type) {
            case 'TEXT':
            case 'MULTI_LINE_TEXT':
                return (
                    <div key={name} className="field">
                        <label htmlFor={name}>{label}</label>
                        <InputText
                            id={name}
                            value={form[name] || ''}
                            placeholder={placeholder}
                            disabled={readonly}
                            onChange={e => handleChange(name, e.target.value)}
                        />
                    </div>
                );

            case 'INTEGER':
            case 'DECIMAL':
                return (
                    <div key={name} className="field">
                        <label htmlFor={name}>{label}</label>
                        <InputNumber
                            id={name}
                            value={form[name]}
                            disabled={readonly}
                            onValueChange={e => handleChange(name, e.value)}
                        />
                    </div>
                );

            case 'DATE':
                return (
                    <div key={name} className="field">
                        <label htmlFor={name}>{label}</label>
                        <Calendar
                            id={name}
                            value={form[name]}
                            disabled={readonly}
                            onChange={e => handleChange(name, e.value)}
                            showIcon
                        />
                    </div>
                );

            case 'BOOLEAN':
                return (
                    <div key={name} className="field-checkbox">
                        <Checkbox
                            inputId={name}
                            checked={form[name] || false}
                            disabled={readonly}
                            onChange={e => handleChange(name, e.checked)}
                        />
                        <label htmlFor={name}>{label}</label>
                    </div>
                );

            case 'ENUM':
                return (
                    <div key={name} className="field">
                        <label htmlFor={name}>{label}</label>
                        <Dropdown
                            id={name}
                            value={form[name]}
                            options={options}
                            optionLabel="label"
                            optionValue="value"
                            onChange={e => handleChange(name, e.value)}
                            placeholder={placeholder}
                            disabled={readonly}
                        />
                    </div>
                );

            case 'REFERENCE':
                return (
                    <div key={name} className="field">
                        <label htmlFor={name}>{label}</label>
                        <div className="p-inputgroup">
                            <InputText value={form[name] || ''} readOnly />
                            <Button icon="pi pi-search" onClick={() => openLookup(lookupId!, name)} disabled={readonly} />
                        </div>
                    </div>
                );

            default:
                return null;
        }
    };

    return (
        <div className="card">
            <h2>{context.title}</h2>
            <form
                onSubmit={e => {
                    e.preventDefault();
                    onSubmit(form);
                }}>
                {context.fields.map(renderField)}
                <div className="mt-3">
                    <Button type="submit" label="ذخیره" icon="pi pi-check" />
                </div>
            </form>

            <Dialog
                header="انتخاب مقدار"
                visible={dialogVisible}
                style={{ width: '50vw' }}
                onHide={() => setDialogVisible(false)}
                footer={
                    <Button label="تأیید" icon="pi pi-check" onClick={confirmLookupSelection} disabled={!selectedLookupItem} />
                }
            >
                <DataTable
                    value={lookupData[context.lookups?.find(l => l.id === activeLookupId)?.id || ''] || []}
                    selectionMode="single"
                    selection={selectedLookupItem}
                    onSelectionChange={e => setSelectedLookupItem(e.value)}
                    paginator rows={5}
                >
                    <Column field="value" header="مقدار" />
                    <Column field="title" header="عنوان" />
                </DataTable>
            </Dialog>
        </div>
    );
}
