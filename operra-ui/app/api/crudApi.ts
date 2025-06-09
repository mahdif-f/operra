// app/api/crudApi.ts

const BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;

export async function fetchAll<T>(entity: string): Promise<T[]> {
    const response = await fetch(`${BASE_URL}/crud/${entity}`);
    if (!response.ok) {
        throw new Error(`خطا در دریافت لیست ${entity}`);
    }
    return await response.json();
}

export async function fetchOne<T>(entity: string, id: number): Promise<T> {
    const response = await fetch(`${BASE_URL}/crud/${entity}/${id}`);
    if (!response.ok) {
        throw new Error(`خطا در دریافت رکورد ${id} از ${entity}`);
    }
    return await response.json();
}

export async function createOne<T>(entity: string, data: T): Promise<T> {
    const response = await fetch(`${BASE_URL}/crud/${entity}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        const err = await response.json();
        throw err;
    }
    return await response.json();
}

export async function updateOne<T>(entity: string, id: number, data: T): Promise<T> {
    const response = await fetch(`${BASE_URL}/crud/${entity}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        const err = await response.json();
        throw err;
    }
    return await response.json();
}

export async function deleteOne(entity: string, id: number): Promise<void> {
    const response = await fetch(`${BASE_URL}/crud/${entity}/${id}`, {
        method: 'DELETE',
    });
    if (!response.ok) {
        throw new Error(`خطا در حذف رکورد ${id} از ${entity}`);
    }
}
