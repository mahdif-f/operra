// app/api/menuUtils.ts

import { MenuItem } from 'primereact/menuitem';
import { useRouter } from 'next/navigation';

export interface MenuItemDTO {
    label: string;
    contextId: string;
    children?: MenuItemDTO[];
}

export function useConvertedMenu(items: MenuItemDTO[]) {
    const router = useRouter();

    const convert = (items: MenuItemDTO[]): MenuItem[] =>
        items.map((item) => ({
            label: item.label,
            icon: 'pi pi-fw pi-folder',
            command: () => router.push(`/${item.contextId}`),
            items: item.children ? convert(item.children) : undefined,
        }));

    return convert(items);
}
